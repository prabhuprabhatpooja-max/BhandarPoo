// Cart counter (stored in the browser only) and mobile menu toggle.
(function () {
    const KEY = 'bhandarpoo-cart-count';
    const countEl = document.getElementById('cart-count');

    function readCount() {
        try { return parseInt(localStorage.getItem(KEY), 10) || 0; } catch (e) { return 0; }
    }

    function writeCount(n) {
        try { localStorage.setItem(KEY, String(n)); } catch (e) { /* storage unavailable */ }
        if (countEl) countEl.textContent = n;
    }

    writeCount(readCount());

    document.querySelectorAll('.add-to-cart').forEach(function (btn) {
        btn.addEventListener('click', function () {
            writeCount(readCount() + 1);
            btn.textContent = 'Added ✓';
            btn.classList.add('added');
            setTimeout(function () {
                btn.textContent = 'Add to Cart';
                btn.classList.remove('added');
            }, 1200);
        });
    });

    const toggle = document.querySelector('.nav-toggle');
    const nav = document.querySelector('.main-nav');
    if (toggle && nav) {
        toggle.addEventListener('click', function () {
            const open = nav.classList.toggle('open');
            toggle.setAttribute('aria-expanded', String(open));
        });
    }
})();
