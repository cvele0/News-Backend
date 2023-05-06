fetch('/api/subjects', {
    method: 'GET'
}).then(response => {
    return response.json();
}).then(subjects => {
    for (const subject of subjects) {
        addSubjectElements(subject)
    }
})

function addSubjectElements(subject) {
    const subjectLinks = document.getElementById('subject-links');

    const linkWrapperDiv = document.createElement('div');

    const subjectLink = document.createElement('a');
    subjectLink.innerText = subject.title;
    subjectLink.href = '/subjects/' + subject.id;


    const removeButton = document.createElement('button');
    removeButton.innerText = 'Remove'
    removeButton.classList.add('btn', 'btn-danger')
    removeButton.style.marginLeft = '10px'
    removeButton.onclick = function (e) {
        deleteSubject(subject.id).then(() => {
            subjectLinks.removeChild(linkWrapperDiv)
        });
    }

    linkWrapperDiv.appendChild(subjectLink);
    linkWrapperDiv.appendChild(removeButton);
    linkWrapperDiv.appendChild(document.createElement('br'));
    linkWrapperDiv.appendChild(document.createElement('br'));

    subjectLinks.appendChild(linkWrapperDiv);
}

function deleteSubject(id) {
    return fetch('/api/subjects/'+id, {
        method: 'DELETE'
    })
}

document.getElementById("subject-form").addEventListener('submit', function(e) {
    e.preventDefault();

    const subjectTitleElement = document.getElementById('subject-title');
    const contentElement = document.getElementById('subject-content');

    const title = subjectTitleElement.value;
    const content = contentElement.value;

    fetch('/api/subjects', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            title: title,
            content: content
        })
    }).then(response => {
        return response.json();
    }).then(subject => {
        addSubjectElements(subject)
        subjectTitleElement.value = '';
        contentElement.value = '';
    })
})