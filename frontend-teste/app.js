const API_BASE_URL = 'http://localhost:8080';

const loginForm = document.getElementById('login-form');
const loginSection = document.getElementById('login-section');
const contentSection = document.getElementById('content-section');
const logoutBtn = document.getElementById('logout-btn');
const fetchAulasBtn = document.getElementById('fetch-aulas-btn');
const aulasContent = document.getElementById('aulas-content');
const loginError = document.getElementById('login-error');

function showContent(isLoggedIn) {
    loginSection.classList.toggle('hidden', isLoggedIn);
    contentSection.classList.toggle('hidden', !isLoggedIn);
}

// Evento de Login
loginForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    loginError.textContent = '';
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    try {
        const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password })
        });

        if (!response.ok) {
            throw new Error('Usuário ou senha inválidos');
        }

        const data = await response.json();
        localStorage.setItem('jwt_token', data.token);
        showContent(true);

    } catch (error) {
        loginError.textContent = error.message;
    }
});

// Evento de Logout
logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('jwt_token');
    showContent(false);
    aulasContent.innerHTML = '';
});

// Evento de Buscar Aulas
fetchAulasBtn.addEventListener('click', async () => {
    const token = localStorage.getItem('jwt_token');
    aulasContent.innerHTML = '';

    if (!token) {
        alert('Você não está logado.');
        return;
    }

    try {
        const response = await fetch(`${API_BASE_URL}/api/aulas`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        if (!response.ok) {
            if (response.status === 403) throw new Error('Acesso negado. Token inválido ou expirado.');
            throw new Error('Erro ao buscar aulas.');
        }

        const aulas = await response.json();
        aulasContent.innerHTML = aulas.map(aula => `<div><h3>${aula.titulo}</h3><p>${aula.conteudo}</p></div>`).join('');

    } catch (error) {
        aulasContent.innerHTML = `<p style="color: red;">${error.message}</p>`;
        // Se o token expirou, desloga o usuário
        if (error.message.includes('expirado')) {
            setTimeout(() => logoutBtn.click(), 2000);
        }
    }
});

// Verifica o estado inicial
const initialToken = localStorage.getItem('jwt_token');
showContent(!!initialToken);