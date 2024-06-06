document.querySelector('#reservation-submit-btn').addEventListener('click', makeReservation)


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
            agreedToTerms: document.querySelector('#agreed-to-terms').checked,
            startDate: document.querySelector('#reservation-start-date').value,
            endDate: document.querySelector('#reservation-end-date').value,
            selectedSiteId: document.querySelector('#selectedSiteId').value
            
        })
    })
    .then(response => {
        if(response.ok){
            // run pop up function
        } else {
            // inform the user that something went wrong
        }
        return response.text()})
    .then(responseMessage => {
        console.log(responseMessage)
    })
    .catch(err => {
        console.log(`error ${err}`)
    })

}










