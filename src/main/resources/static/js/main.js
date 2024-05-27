const slider = document.querySelector('.img-slider')
let sliderCurrentIndex = 0


setSilderClock()

function setSilderClock(){
    setTimeout(changeImageOnSlider, 3000)
}


function changeImageOnSlider(){

    for(let i = 0; i < slider.children.length; i++){
        slider.children[i].style.zIndex = 0
        slider.children[i].style.opacity = 0
    }

    if (sliderCurrentIndex + 1 < slider.children.length){
        sliderCurrentIndex++ 
    }else{
        sliderCurrentIndex = 0
    }
    

    slider.children[sliderCurrentIndex].style.zIndex = 1
    slider.children[sliderCurrentIndex].style.opacity = 1

    setSilderClock()


}