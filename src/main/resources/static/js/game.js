document.addEventListener('DOMContentLoaded', function() {
    const board = document.getElementById('board');
    const messageDiv = document.getElementById('message');
    const resignBtn = document.getElementById('resignBtn');
    let stompClient = null;
    let selectedPiece = null;

    // Initialize WebSocket connection
    function connect() {
        const socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/game/' + gameId, function(gameUpdate) {
                updateGameState(JSON.parse(gameUpdate.body));
            });
        });
    }

    // Initialize 4x4 board
    function initBoard() {
        board.innerHTML = '';
        const letters = ['a', 'b', 'c', 'd'];
        
        // Create column labels
        const colLabels = document.createElement('div');
        colLabels.className = 'col-labels';
        letters.forEach(letter => {
            const label = document.createElement('span');
            label.textContent = letter;
            colLabels.appendChild(label);
        });
        board.appendChild(colLabels);

        // Create board squares
        for (let row = 4; row >= 1; row--) {
            // Row label
            const rowLabel = document.createElement('span');
            rowLabel.className = 'row-label';
            rowLabel.textContent = row;
            board.appendChild(rowLabel);
            
            // Squares
            for (let col = 0; col < 4; col++) {
                const square = document.createElement('div');
                square.className = `square ${(row + col) % 2 === 0 ? 'light' : 'dark'}`;
                square.id = `${letters[col]}${row}`;
                square.dataset.row = row;
                square.dataset.col = col;
                
                square.addEventListener('click', handleSquareClick);
                board.appendChild(square);
            }
        }
    }

    // Validate 4x4 checker moves
    function isValidMove(from, to) {
        const fromCol = from.charCodeAt(0) - 97; // a=0, b=1, etc.
        const fromRow = parseInt(from.charAt(1));
        const toCol = to.charCodeAt(0) - 97;
        const toRow = parseInt(to.charAt(1));
        
        // Basic move validation for 4x4 checkers
        const colDiff = Math.abs(toCol - fromCol);
        const rowDiff = toRow - fromRow;
        
        // White moves up (row increases), Black moves down (row decreases)
        const direction = game.currentTurn === 'WHITE' ? 1 : -1;
        
        return colDiff === 1 && rowDiff === direction;
    }

    function handleSquareClick(event) {
        const square = event.currentTarget;
        if (square.classList.contains('valid-move') && selectedPiece) {
            // Make move
            const from = selectedPiece.id;
            const to = square.id;
            stompClient.send("/app/game/move", {}, 
                JSON.stringify({'gameId': gameId, 'from': from, 'to': to}));
            clearSelection();
        } else {
            // Select piece
            const piece = square.querySelector('.piece');
            if (piece) {
                clearSelection();
                selectedPiece = piece;
                piece.classList.add('selected');
                // TODO: Highlight valid moves
            }
        }
    }

    function clearSelection() {
        if (selectedPiece) {
            selectedPiece.classList.remove('selected');
            selectedPiece = null;
        }
        document.querySelectorAll('.valid-move').forEach(el => {
            el.classList.remove('valid-move');
        });
    }

    function updateGameState(game) {
        // Update game info
        document.querySelector('.status').textContent = game.status;
        
        // Update turn indicators
        document.querySelectorAll('.turn-indicator').forEach(el => {
            el.classList.remove('active');
        });
        const currentTurnIndicator = document.querySelector(
            `.${game.currentTurn.toLowerCase()}-player .turn-indicator`);
        if (currentTurnIndicator) {
            currentTurnIndicator.classList.add('active');
        }

        // Update board
        clearBoard();
        if (game.pieces) {
            game.pieces.forEach(piece => {
                const square = document.getElementById(piece.position);
                if (square) {
                    const pieceDiv = document.createElement('div');
                    pieceDiv.className = `piece ${piece.color.toLowerCase()}`;
                    pieceDiv.id = piece.position;
                    if (piece.king) {
                        pieceDiv.classList.add('king');
                    }
                    square.appendChild(pieceDiv);
                }
            });
        }

        // Update move history
        if (game.moves) {
            const moveHistory = document.getElementById('move-history');
            moveHistory.innerHTML = '<h3>Move History</h3>';
            game.moves.forEach(move => {
                const moveEl = document.createElement('div');
                moveEl.className = 'move';
                moveEl.textContent = `${move.player}: ${move.from} → ${move.to}`;
                moveHistory.appendChild(moveEl);
            });
        }
    }

    function clearBoard() {
        document.querySelectorAll('.piece').forEach(p => p.remove());
    }

    // Handle resign
    resignBtn.addEventListener('click', function() {
        if (confirm('Are you sure you want to resign?')) {
            stompClient.send("/app/game/resign", {}, JSON.stringify({'gameId': gameId}));
        }
    });

    // Initialize
    initBoard();
    connect();
});
