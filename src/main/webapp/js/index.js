function loadTask() {
    $.ajax({
        url: 'tasklist?action=read'
    }).done(function (response) {
        putTaskInHTML(response.task);
    });
}

function putTaskInHTML(task) {
    var ul = document.getElementsByTagName('ul')[0];
    var list = '';
    for (var i = 0; i < task.length; i++) {
        var tasks = task[i].task;
        var tasksId = task[i].id;

        var taskList = '<li><div class="TaskArea"> ' +
            '<div class="row">' +
            '<input type="checkbox" value="' + tasksId + '" id="checkDoneTask" onclick=markDone("' + tasksId + '")>' +
            '<p class="col-md-6">' + tasks + '</p>' +
            '<button class="deleteButton" onclick="deleteTask(' + tasksId + ')">Delete</button>' +
            '</div>' +
            '</div></li>';

        list = list + taskList;
    }
    ul.innerHTML = list;
    $(task)
}

loadTask();

function addTask() {
    var nametodo = document.getElementById('task').value;

    if (nametodo.trim().length > 0) {
        $.ajax({
            url: 'tasklist?action=write&task=' + nametodo
        }).done(function (response) {
            location.href = "index.jsp";
        });
    }
    else {
        var alertDiv = document.createElement("p");
        alertDiv.setAttribute("id", "alertMessage");
        var alertContent = document.createTextNode("You must insert data!");
        alertDiv.appendChild(alertContent);
        var fieldsDiv = document.getElementById("sendSomething");
        fieldsDiv.appendChild(alertDiv);
    }

}

function deleteTask(id) {
    $.ajax({
        url: 'tasklist?action=delete&id=' + id
    }).done(function (response) {
        location.href = "index.jsp";
    });
}

function markDone(id) {
    $.ajax({
        url: 'tasklist?action=markdone&id=' + id
    }).done(function (response) {
        location.href = "index.jsp";
    });
}


function loadDoneTasks() {
    $.ajax({
        url: 'tasklist?action=readDoneTask'
    }).done(function (response) {
        putDoneTasksInHTML(response.donetasks);
    });
}

function putDoneTasksInHTML(donetasks) {
    var ul = document.getElementById('doneTasks').getElementsByTagName('ul')[0];
    var list = '';
    for (var i = 0; i < donetasks.length; i++) {
        var doneTasks = donetasks[i];

        var doneTaskList = '<li><div class="TaskArea">' +
            '<div class="row">' +
            '<p class="col-md-6">' + doneTasks.task + '</p>' +
            '</div>' +
            '</div>' +
            '</li>';

        list = list + doneTaskList;
    }
    ul.innerHTML = list;
    $(donetasks)
}

loadDoneTasks();