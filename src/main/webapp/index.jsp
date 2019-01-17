<%

    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    if (o == null) {
        response.sendRedirect("login.html");
    }
%>

<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="css/css.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<header>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Yes_check.svg/600px-Yes_check.svg.png"
             width="50px" height="50px">
        <h1>To Do List</h1>
        <p><a href="logout">Logout</a></p>
    </div>

</header>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 ">
            <div class="todolist not-done">
                <h2>Todos</h2>
                <input type="text" class="form-control add-todo" id="task" placeholder="Add todo">
                <button id="checkAll" class="btn btn-success" onclick="addTask()">Add Task</button>

                <hr>
                <ul id="sortable" class="list-unstyled">
                    <li class="ui-state-default">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value=""/>Take out the trash
                            </label>
                        </div>
                    </li>
                    <li class="ui-state-default">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value=""/>Buy bread</label>
                        </div>
                    </li>
                    <li class="ui-state-default">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" value=""/>Teach penguins to fly</label>
                        </div>
                    </li>
                </ul>
            </div>
        </div>

        <div class="col-md-6">
            <div id="doneTasks" class="todolist">
                <h1>Already Done</h1>
                <ul id="done-items" class="list-unstyled">

                </ul>
            </div>
        </div>
    </div>
</div>


<script>
    function loadTask() {
        $.ajax({
            url: 'tasklist?action=read'
        }).done(function (response) {
            putTaskInHTML(response.task);
        });
    }

    function putTaskInHTML(task) {
        var divTask = document.getElementById('sortable');
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
        var divTask = document.getElementById('sortable');
        var ul = document.getElementById('doneTasks').getElementsByTagName('ul')[0];
        var list = '';
        for (var i = 0; i < donetasks.length; i++) {
            var doneTasks = donetasks[i];
            var doneTasksId = donetasks[i].id;

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

</script>
</body>
