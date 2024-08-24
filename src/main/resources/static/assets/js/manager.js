let lastScrollY = window.scrollY;

window.addEventListener('scroll', function() {
    const header = document.querySelector('.manager-header');
    const currentScrollY = window.scrollY;

    if (currentScrollY === 0) {
        header.classList.remove('hidden');
    } else if (currentScrollY > lastScrollY) {
        header.classList.add('hidden');
    } else {
        header.classList.remove('hidden');
    }

    lastScrollY = currentScrollY;
});