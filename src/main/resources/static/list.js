const deleteButton = document.getElementById('delete-btn');

if (deleteButton) {
    deleteButton.addEventListener('click', event => {
        event.preventDefault(); // 폼 자동 제출 방지
        const postId = document.getElementById('post-id').value;
        const userId = document.getElementById('user-id').value;

        if (confirm('게시글을 삭제하시겠습니까?')) {
            fetch(`/post-management/${userId}/${postId}`, {
                method: 'DELETE'
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('네트워크 응답이 올바르지 않습니다.');
                    }
                    return response.json();
                })
                .then(data => {
                    alert(data.message);
                    location.replace('/posts');
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('오류가 발생했습니다. 다시 시도해주세요.');
                });
        }
    });


    function submitComment() {
        const form = document.getElementById('commentForm');
        const postId = document.getElementById('postId').value;
        const userId = document.getElementById('userId').value;
        const content = document.getElementById('content').value;

        // Constructing the request body for the POST request
        const formData = new FormData();
        formData.append('content', content);

        fetch(`api.hell-low.com/comment-management/${postId}/comments/${userId}`, {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not OK');
                }
                return response.json();
            })
            .then(data => {
                alert('댓글이 성공적으로 등록되었습니다.');
                // Optionally refresh the comments section or reset the form
                form.reset();
            })
            .catch(error => {
                console.error('Error:', error);
                alert('댓글 등록에 실패했습니다. 다시 시도해주세요.');
            });
    }
}