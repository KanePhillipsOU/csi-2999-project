document.querySelector('#reservation-submit-btn').addEventListener('click', makeReservation);

function makeReservation(event) {
    event.preventDefault();

    const firstName = document.querySelector('#first-name');
    const lastName = document.querySelector('#last-name');
    const email = document.querySelector('#email');
    const phone = document.querySelector('#phone');
    const agreedToTerms = document.querySelector('#agreed-to-terms');
    const startDate = document.querySelector('#reservation-start-date');
    const endDate = document.querySelector('#reservation-end-date');
    const selectedSiteId = document.querySelector('#selectedSiteId');

    // Check for empty fields and validity
    if (!firstName.value.trim()) {
        alert('Please enter your first name.');
        firstName.focus();
        return;
    }
    
    if (!lastName.value.trim()) {
        alert('Please enter your last name.');
        lastName.focus();
        return;
    }

    if (!email.value.trim()) {
        alert('Please enter your email.');
        email.focus();
        return;
    }

    if (!isValidEmail(email.value.trim())) {
        alert('Please enter a valid email address (e.g., user@example.com).');
        email.focus();
        return;
    }

    if (!phone.value.trim()) {
        alert('Please enter your phone number.');
        phone.focus();
        return;
    }

    if (!isValidPhoneNumber(phone.value.trim())) {
        alert('Please enter a valid 10-digit phone number.');
        phone.focus();
        return;
    }

    if (!agreedToTerms.checked) {
        alert('You must agree to the terms and conditions to make a reservation!');
        return;
    }

    if (!startDate.value.trim()) {
        alert('Start date is missing.');
        return;
    }

    if (!endDate.value.trim()) {
        alert('End date is missing.');
        return;
    }

    if (!selectedSiteId.value.trim()) {
        alert('Please select a site.');
        return;
    }

    fetch("/reservation", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            firstName: firstName.value.trim(),
            lastName: lastName.value.trim(),
            email: email.value.trim(),
            phoneNumber: phone.value.trim(),
            agreedToTerms: agreedToTerms.checked,
            startDate: startDate.value.trim(),
            endDate: endDate.value.trim(),
            selectedSiteId: selectedSiteId.value.trim()
        })
    })
    .then(response => {
        if (response.ok) {
            const reservationDetails = {
                firstName: firstName.value.trim(),
                lastName: lastName.value.trim(),
                email: email.value.trim(),
                phoneNumber: phone.value.trim(),
                startDate: startDate.value.trim(),
                endDate: endDate.value.trim(),
                siteId: selectedSiteId.value.trim()
            };
    
            // Construct the alert message with reservation details
            const alertMessage = `Reservation made successfully!\n\nDetails:\nFirst Name: ${reservationDetails.firstName}\nLast Name: ${reservationDetails.lastName}\nEmail: ${reservationDetails.email}\nPhone Number: ${reservationDetails.phoneNumber}\nStart Date: ${reservationDetails.startDate}\nEnd Date: ${reservationDetails.endDate}\nSite ID: ${reservationDetails.siteId}`;
    
            // Show the alert message
            alert(alertMessage);
    
            // Redirect to the home page
            window.location.href = '/home'; // Change '/home' to the desired home page URL
        } else {
            alert('Something went wrong, please try again.');
        }
        return response.text();
    })
    .then(responseMessage => {
        console.log(responseMessage);
    })
    .catch(err => {
        console.log(`error ${err}`);
    });
}

function isValidEmail(email) {
    // Enhanced regex for basic email validation
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
}

function isValidPhoneNumber(phoneNumber) {
    // Regex for validating US phone number format: 10 digits, no spaces or symbols
    const phonePattern = /^\d{10}$/;
    return phonePattern.test(phoneNumber);
}
