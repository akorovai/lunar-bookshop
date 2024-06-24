const overlay = document.getElementById('overlay');


function openOverlay(message) {
    overlay.classList.add('overlay-active');
    document.body.classList.add('overlay-open');
    document.getElementById('overlay-message').textContent = message;
}

function closeOverlay() {
    overlay.classList.remove('overlay-active');
    document.body.classList.remove('overlay-open');
}

window.addEventListener('click', function (event) {
    if (event.target === overlay) {
        closeOverlay();
    }
});

function getPromotionIdFromURL() {
    const pathArray = window.location.pathname.split('/');
    return pathArray[pathArray.length - 3];
}

function assignToPromotion(isbn) {
    const promotionId = getPromotionIdFromURL();


    fetch(`/assignToPromotion/${isbn}/${promotionId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    }).then((response) => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Failed to assign book to promotion');
        }
    }).then((data) => {
        console.log('Books:', data);
        openOverlay(`Książka ${isbn} przepisana do promocji ${promotionId}`);
        setTimeout(function() {
            window.location.href = '/promotions';
        }, 5000);
    }).catch((error) => {
        console.error('Error:', error);
        alert('An error occurred while assigning the book to promotion');
    });
}

