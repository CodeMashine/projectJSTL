const deleteBtns = document.querySelectorAll(".delete-quest");

deleteBtns.forEach(btn => {
    const currentId = btn.dataset.questid;

    btn.addEventListener("click", async () => {
        if (confirm("Delete quest ?")) {
            const response = await fetch(`/delete-quest?id=${currentId}`, {
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