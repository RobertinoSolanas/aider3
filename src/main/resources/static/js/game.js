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

    // Initialize board
    function initBoard() {
        board.innerHTML = '';
        const letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'];
        
        for (let row = 8; row >= 1; row--) {
            for (let col = 0; col < 8; col++) {
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
        document.querySelector('.game-info div:nth-child(3) span').textContent = game.status;
        document.querySelector('.game-info div:nth-child(4) span').textContent = game.currentTurn;

        // Update board
        clearBoard();
        game.pieces.forEach(piece => {
            const square = document.getElementById(piece.position);
            if (square) {
                const pieceDiv = document.createElement('div');
                pieceDiv.className = `piece ${piece.color.toLowerCase()}`;
                pieceDiv.id = piece.position;
                if (piece.type === 'KING') {
                    pieceDiv.classList.add('king');
                }
                square.appendChild(pieceDiv);
            }
        });
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
