document.querySelector('#revervation-submit-btn').addEventListener('click', makeReservation)


function makeReservation(){

    event.preventDefault()

    // Add code here that prevents this from going through if the
    // user does not agree to the terms

    fetch("/reservation",{
        method: 'post',
        headers:{'Content-Type': 'application/json'},
        body: JSON.stringify({
            firstName: document.querySelector('#first-name').value,
            lastName: document.querySelector('#last-name').value,
            email: document.querySelector('#email').value,
            phoneNumber: document.querySelector('#phone').value
        })
    })
    .then(response => response.text())
    .then(responseMessage => {
        console.log(responseMessage)
    })
    .catch(err => {
        console.log(`error ${err}`)
    })

}