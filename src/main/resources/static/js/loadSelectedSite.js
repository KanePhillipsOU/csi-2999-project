function handleButtonClick(event) {
    const buttons = document.querySelectorAll('.selectBtn');
    
    buttons.forEach(button => {
        button.classList.remove('clicked');
    });
    event.target.classList.add('clicked');
}

const buttons = document.querySelectorAll('.selectBtn');
buttons.forEach(button => {
    button.addEventListener('click', handleButtonClick);
});

//Added to new js file to avoid conflicts with other click requirements in reservation.js