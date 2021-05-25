document.write('<nav class="navbar navbar-expand-lg navbar-dark bg-dark">\
<div class="container">\
    <a class="navbar-brand" href="#">Grupo21</a>\
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>\
    <div class="collapse navbar-collapse" id="navbarSupportedContent">\
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">\
            <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Home</a></li>\
            <li class="nav-item dropdown">\
                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Usuarios <span class="badge badge-dark" th:text="${userCount}">#</span></a>\
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">\
                    <li><a class="dropdown-item" th:href="@{/user/abm}" sec:authorize="hasRole('+"'"+'ROLE_ADMIN'+"'"+')">ABM</a></li>\
                    <li><a class="dropdown-item" th:href="@{/user}" sec:authorize="hasRole('+"'"+'ROLE_ADMIN'+"'"+') or hasRole('+"'"+'ROLE_AUDITOR'+"'"+')">Listado</a></li>\
                    <li><a class="dropdown-item" th:href="@{/user/pdf}" sec:authorize="hasRole('+"'"+'ROLE_AUDITOR'+"'"+')">Generar PDF</a></li>\
                </ul>\
            </li>\
            <li class="nav-item dropdown">\
                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Perfiles <span class="badge badge-dark" th:text="${userRoleCount}">#</span></a>\
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">\
                    <li><a class="dropdown-item" th:href="@{/userrole/abm}" sec:authorize="hasRole('+"'"+'ROLE_ADMIN'+"'"+')">ABM</a></li>\
                    <li><a class="dropdown-item" th:href="@{/userrole}" sec:authorize="hasRole('+"'"+'ROLE_ADMIN'+"'"+') or hasRole('+"'"+'ROLE_AUDITOR'+"'"+')">Listado</a></li>\
                    <li><a class="dropdown-item" th:href="@{/userrole/pdf}" sec:authorize="hasRole('+"'"+'ROLE_AUDITOR'+"'"+')">Generar PDF</a></li>\
                </ul>\
            </li>\
            <li class="nav-item dropdown">\
                <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false" th:text="${username}">Sesión</a>\
                <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">\
                    <li><a class="dropdown-item" th:href="@{/login}" sec:authorize="isAnonymous()">Login</a></li>\
                    <li><a class="dropdown-item" th:href="@{/logout}" sec:authorize="isAuthenticated()">Logout</a></li>\
                </ul>\
            </li>\
        </ul>\
    </div>\
</div>\
</nav>');