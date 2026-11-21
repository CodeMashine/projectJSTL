const deleteBtns = document.querySelectorAll(".delete-user");

deleteBtns.forEach(btn => {
    const currentId = btn.dataset.userid;

    btn.addEventListener("click", async () => {
        if (confirm("Delete user id :"+currentId+"?")) {
            const response = await fetch(`/delete-profile?id=${currentId}`, {
                method: 'POST'
            });

            if (response.ok) {
                window.location.reload();
            } else {
                alert('Delete fault');
            }
        }
    });
});