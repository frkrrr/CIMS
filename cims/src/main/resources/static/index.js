function updateNavbar(user) {
    document.getElementById('user-name').textContent = `${user.nom} ${user.prenom}`;
}

function openForm() {
    document.getElementById("myModal").style.display = "block";
}

function closeForm() {
    document.getElementById("myModal").style.display = "none";
}

function openCardModal(cardType) {
    const title = cardType === 'rente-scolaire' ? 'Rentrée Scolaire' : 'Journée de la Science';
    document.getElementById('modalTitle').textContent = title;

    const tbody = document.getElementById('cardTable').getElementsByTagName('tbody')[0];
    tbody.innerHTML = '';

    const exampleData = [
        { nom: 'Jane', prenom: 'Smith', bulletin: '' },
        { nom: 'Sam', prenom: 'Brown', bulletin: '' }
    ];

    exampleData.forEach(data => {
        const newRow = tbody.insertRow();
        newRow.insertCell(0).textContent = data.nom;
        newRow.insertCell(1).textContent = data.prenom;

        const fileInputCell = newRow.insertCell(2);
        const fileInput = document.createElement('input');
        fileInput.type = 'file';
        fileInput.name = 'bulletin';
        fileInputCell.appendChild(fileInput);
    });

    document.getElementById("cardModal").style.display = "block";
}

function closeCardModal() {
    document.getElementById("cardModal").style.display = "none";
}

function confirmAction() {
    alert('Action confirmed!');
}

document.getElementById("addEnfantForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    formData.append('matricule', localStorage.getItem('matricule'));

    fetch('/api/enfants/employees/${matricule}/enfants', {
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            if (data) {
                addRowToTable(data);
                closeForm();
            } else {
                alert('Failed to add child. Please try again.');
            }
        })
        .catch(error => {
            console.error('Error adding child:', error);
            alert('An error occurred. Please try again.');
        });
});

function addRowToTable(child) {
    const table = document.getElementById("enfants-table").getElementsByTagName('tbody')[0];
    const newRow = table.insertRow();

    newRow.insertCell(0).textContent = child.nom;
    newRow.insertCell(1).textContent = child.prenom;
    newRow.insertCell(2).textContent = child.age;
    newRow.insertCell(3).textContent = child.classe;
}

window.onclick = function(event) {
    if (event.target === document.getElementById("myModal")) {
        closeForm();
    } else if (event.target === document.getElementById("cardModal")) {
        closeCardModal();
    }
}
function fetchChildren(matricule) {
    fetch(`http://localhost:8080/api/enfants/by-employee?matricule=${matricule}`) // use the passed matricule
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById("enfants-table").getElementsByTagName('tbody')[0];
            tbody.innerHTML = '';

            data.forEach(child => {
                addRowToTable(child);
            });
        })
        .catch(error => {

            console.error('Error fetching children:', error);
            alert('An error occurred. Please try again.');
        });
}


window.onload = function() {
    const user = JSON.parse(localStorage.getItem('user'));
    if (user && user.matricule) {
        updateNavbar(user);
        fetchChildren(user.matricule); // Pass the matricule directly
    } else {
        // If no user is found in localStorage, redirect to the login page
        window.location.href = 'http://localhost:63342/cims/static/authentification.html';
    }

    document.getElementById('disconnect').addEventListener('click', function() {
        localStorage.removeItem('user');
        window.location.href = 'http://localhost:63342/cims/static/authentification.html';
    });
};
