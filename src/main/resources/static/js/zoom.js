document.addEventListener('DOMContentLoaded', function () {
    const mapContainer = document.querySelector('.map-zoom-container');
    const mapImg = document.querySelector('.mapImg');
    const overlay = document.querySelector('.overlay');
    let scale = 1;
    const scaleStep = 0.1;
    let originX = 0;
    let originY = 0;
  
    mapContainer.addEventListener('wheel', function (event) {
      event.preventDefault();
      
      // Hide the overlay on the first scroll event
      if (overlay) {
        overlay.style.opacity = 0;
        setTimeout(() => overlay.style.display = 'none', 500); // Remove the overlay after the transition
      }
      
      // Get the mouse position relative to the container
      const rect = mapContainer.getBoundingClientRect();
      const mouseX = event.clientX - rect.left;
      const mouseY = event.clientY - rect.top;
  
      // Adjust the scale
      if (event.deltaY < 0) {
        scale += scaleStep;
      } else {
        scale -= scaleStep;
        if (scale < 1) scale = 1; // Prevent shrinking below original size
      }
  
      // Calculate the new transform origin
      originX = mouseX / mapContainer.offsetWidth * 100;
      originY = mouseY / mapContainer.offsetHeight * 100;
  
      // Apply the zoom and origin
      mapImg.style.transformOrigin = `${originX}% ${originY}%`;
      mapImg.style.transform = `scale(${scale})`;
    });
  });
  