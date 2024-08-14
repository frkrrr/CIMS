document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault();

    var formData = new FormData(this);

    fetch('http://localhost:8080/api/validate-matricule', {  // Update the URL as necessary
        method: 'POST',
        body: formData
    })
        .then(response => {
            console.log('Response status:', response.status);
            return response.json();
        })
        .then(data => {
            console.log('Response data:', data);
            if (data.success) {
// Store user details in localStorage
                localStorage.setItem('user', JSON.stringify(data.user));
               // localStorage.setItem('matricule', data.user.matricule);
                window.location.href = 'http://localhost:63342/cims/static/index.html'; // Redirect to main page
            } else {
                document.getElementById("errorMessage").innerText = 'Login failed. Please check your matricule and try again.';
            }
        })
        .catch(error => {
            console.error('Error during login:', error);
            document.getElementById("errorMessage").innerText = 'An error occurred. Please try again.';
        });
});