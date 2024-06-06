document.querySelector('#reservation-submit-btn').addEventListener('click', makeReservation)


    // Perform form validation here
    if (!document.getElementById('reservationForm').checkValidity()) {
        alert('Please fill out all required fields correctly.');
        return; // Exit the function if form validation fails
    }
    
    // If form validation passes, proceed with making the reservation
    fetch("/reservation", {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstName: document.querySelector('#first-name').value,
            lastName: document.querySelector('#last-name').value,
            email: document.querySelector('#email').value,
            phone: document.querySelector('#phone').value,
            agreedToTerms: document.querySelector('#agreed-to-terms').checked,
            selectedSiteId: document.querySelector('input[name="selectedSite"]:checked').value, // Include selected site ID
            startDate: document.querySelector('#reservation-start-date').value, // Include reservation start date
            endDate: document.querySelector('#reservation-end-date').value // Include reservation end date
        })
    })
    .then(response => response.text())
    .then(responseMessage => {
        console.log(responseMessage);
        // Optionally, redirect to a success page or display a success message to the user
    })
    .catch(err => {
        console.log(`error ${err}`);
        // Handle errors, such as displaying an error message to the user
    });
    