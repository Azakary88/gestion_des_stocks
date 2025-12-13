// ========= CONFIG =========
const API_BASE = "";

// ========= TOKEN =========
function appGetToken() {
    return localStorage.getItem("inventory_token");
}

function appSaveToken(token) {
    localStorage.setItem("inventory_token", token);
}

function appLogout() {
    localStorage.removeItem("inventory_token");
    location.href = "/login.html";
}

// ========= AUTH =========
async function appLogin(email, password) {
    const res = await fetch(API_BASE + "/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });

    if (!res.ok) {
        throw new Error("Identifiants invalides");
    }

    const json = await res.json();
    if (!json.token) throw new Error("Token manquant");

    appSaveToken(json.token);
    return json.token;
}

// ========= API FETCH WRAPPER =========
async function appFetch(path, opts = {}) {
  const token = appGetToken();

  const headers = Object.assign(
    {
      "Content-Type": "application/json"
    },
    opts.headers || {}
  );

  if (token) headers['Authorization'] = 'Bearer ' + token;

  const final = Object.assign({ headers }, opts);

  const res = await fetch(API_BASE + path, final);

  if (res.status === 401 || res.status === 403) {
    appLogout();
    location.href = '/login.html';
    throw new Error("Unauthorized");
  }

  if (res.status === 204) return null;

  const text = await res.text();

  try { return text ? JSON.parse(text) : null; }
  catch (_) { return text; }
}


// RENDRE LES FONCTIONS GLOBALES (clé du problème)
window.appLogin = appLogin;
window.appLogout = appLogout;
window.appFetch = appFetch;
