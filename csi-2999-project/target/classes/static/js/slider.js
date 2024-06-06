const slider = document.querySelector('.img-slider')
let sliderCurrentIndex = 0


setSilderClock()

function setSilderClock(){
    setTimeout(changeImageOnSlider, 6000)
}


function changeImageOnSlider(){

    if (sliderCurrentIndex + 1 < slider.children.length){
        sliderCurrentIndex++ 
    }else{
        sliderCurrentIndex = 0
    }

    for(let i = 0; i < slider.children.length; i++){
        slider.children[i].style.zIndex = 0
        slider.children[i].style.transition = 'none'
        if(i !== sliderCurrentIndex - 1 && !(sliderCurrentIndex === 0 && i === slider.children.length - 1)){
            slider.children[i].style.opacity = 0
        }
    }
    

    slider.children[sliderCurrentIndex].style.zIndex = 1
    slider.children[sliderCurrentIndex].style.opacity = 1
    slider.children[sliderCurrentIndex].style.transition = 'opacity 2s'


    setSilderClock()


}