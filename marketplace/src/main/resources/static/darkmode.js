window.addEventListener('DOMContentLoaded', function () {

    // Apply saved dark mode preference
    if (localStorage.getItem('darkMode') === 'true') {
        document.body.classList.add('dark-mode');
    }

    // Dark mode toggle (for settings page)
    const toggle = document.getElementById('darkModeToggle');
    if (toggle) {
        toggle.checked = localStorage.getItem('darkMode') === 'true';
        toggle.addEventListener('change', function () {
            document.body.classList.toggle('dark-mode', this.checked);
            localStorage.setItem('darkMode', this.checked);
        });
    }

    // ✅ Logout confirmation on ALL pages automatically
    document.addEventListener('click', function (e) {
        const link = e.target.closest('a[href="/logout"]');
        if (link) {
            e.preventDefault();
            if (confirm('Are you sure you want to logout?')) {
                window.location.href = '/logout';
            }
        }
    });
});