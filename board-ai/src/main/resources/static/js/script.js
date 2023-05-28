// script.js
function toggleReplyForm() {
    let replyForm = document.getElementById('replyForm');
    if (replyForm.style.display === 'none') {
        replyForm.style.display = 'block';
    } else {
        replyForm.style.display = 'none';
    }
}


window.addEventListener('DOMContentLoaded', () => {
    const images = document.querySelectorAll('.image-thumbnail');

    images.forEach((image) => {
        image.addEventListener('click', (event) => {
            const enlargedImage = document.createElement('img');
            enlargedImage.src = event.target.src;
            enlargedImage.classList.add('enlarged-image');

            const overlay = document.createElement('div');
            overlay.classList.add('overlay');

            overlay.appendChild(enlargedImage);
            document.body.appendChild(overlay);

            overlay.addEventListener('click', () => {
                overlay.remove();
            });
        });
    });
});
