document.querySelector('#revervation-submit-btn').addEventListener('click', makeReservation)


function makeReservation(){

    event.preventDefault()


    fetch("/reservation",{
        method: 'post',
        headers:{'Content-Type': 'application/json'},
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
        console.log(responseMessage)
    })
    .catch(err => {
        console.log(`error ${err}`)
    })

}