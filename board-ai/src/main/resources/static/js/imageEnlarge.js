window.addEventListener('DOMContentLoaded', () => {
    const images = document.querySelectorAll('.image-thumbnail');

    images.forEach((image) => {
        image.addEventListener('click', (event) => {
            event.target.classList.toggle('enlarged');
        });
    });
});