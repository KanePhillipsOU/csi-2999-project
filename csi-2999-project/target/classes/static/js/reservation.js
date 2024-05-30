function makeReservation(event) {
    event.preventDefault();
    
    fetch("/reservation", {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            firstName: document.querySelector('#first-name').value,
            lastName: document.querySelector('#last-name').value,
            email: document.querySelector('#email').value,
            phoneNumber: document.querySelector('#phone').value,
            agreedToTerms: document.querySelector('#agreed-to-terms').checked
        })
    })
    .then(response => response.text())
    .then(responseMessage => {
        console.log(responseMessage);
        // Additional logic to handle successful reservation
    })
    .catch(err => {
        console.error('Error:', err);
        // Additional logic to handle error
    });
}

document.querySelector('#reservation-submit-btn').addEventListener('click', makeReservation);
