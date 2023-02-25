function navigateToUserPage() {
    const userId = document.getElementById("nameInput").value;
    const url = `user?id=${userId}`;
    window.location.href = url;   
}

function deleteUser() {
    const userId = document.getElementById("input").value;
    const page = `user?id=${userId}`;  

    fetch(page, {
        method: 'DELETE',
    }).then(response => {
        if (response.ok) {
            alert("User with ID " + userId + " has been deleted.");
        } else {
            alert("User not found");
        }
    });
  }
