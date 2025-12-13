async function appLogin() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const response = await fetch("/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });

    if (!response.ok) {
        alert("Identifiants incorrects");
        return;
    }

    const data = await response.json();
    localStorage.setItem("token", data.token);

    // Redirection vers la page produits
    window.location.href = "/products.html";
}
