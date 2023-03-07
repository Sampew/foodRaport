function submitForm() {
    const myId = document.getElementById("myId").value;
    const form = document.getElementById("myForm");
    form.action = `http://localhost:8080/user/${myId}`;
    form.submit();
}

function submitGetAllForm() {
    const form = document.getElementById("getAllForm");
    form.action = `http://localhost:8080/user`;
    form.submit();
}

