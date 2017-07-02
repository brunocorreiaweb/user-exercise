
var UserController = function() {

    var User  = function (name, dob) {
        this.name = name;
        this.dob = dob;
        this.isValid = function () {
            if(isEmpty(this.name) || isEmpty(this.dob)){
                return false;
            }
            return true;
        };
    };

    var BackendUtils = {
        ENDPOINT: "http://localhost:8080/",
        Services: {
            USERS: "users"
        }
    },
    Messages = {
        USER_CREATED_SUCCESSFULLY: 'User created successfully',
        USER_CREATION_ERROR: 'Error creating user',
        INVALID_USER_DATA: 'Invalid user data!',
        USER_DELETED_SUCCESSFULLY:'User deleted successfully!',
        USER_DELETED_ERROR:'Error deleting user!',
        USER_UPDATED_SUCCESSFULLY:'User updated successfully!',
        USER_UPDATED_ERROR:'Error updating user!',
        ERROR_LOADING_USERS: 'Error loading users!'
    },
    get = function (url, successCallback, errorCallback){
        $.ajax({
            type: "GET",
            url: BackendUtils.ENDPOINT + url,
            success: successCallback,
            error: errorCallback
        });
    },
    deleteHttp = function (url, successCallback, errorCallback){
        $.ajax({
            type: "DELETE",
            url: BackendUtils.ENDPOINT + url,
            success: successCallback,
            error: errorCallback
        });
    },
    post = function (url, data, successCallback, errorCallback){
        $.ajax({
            type: "POST",
            url: BackendUtils.ENDPOINT + url,
            data: data,
            success: successCallback,
            error: errorCallback
        });
    },
    put = function (url, data, successCallback, errorCallback){
        $.ajax({
            type: "PUT",
            url: BackendUtils.ENDPOINT + url,
            data: data,
            success: successCallback,
            error: errorCallback
        });
    };

    var convertDateToMillis = function (date) {
        return new Date(date).getTime();
    },
    isEmpty = function (value) {
        return (value === undefined || value === "" );
    },
    initDatePicker = function (){
        $('[data-toggle="datepicker"]').datepicker();
    },
    clearCreateErrorMessages = function (){
        var errorMessages = $("#createErrorMessages");
        errorMessages.html("");
    },
    showCreateErrorMessage = function (errorMessage){
        var errorMessages = $("#createErrorMessages");
        errorMessages.html('<p>'+errorMessage+'</p>');
    },
    readUser = function (){
        return new User($('#name').val(),$('#dob').val());
    },
    createUser = function (user){
        var successCallback = function (data){
            showCreateErrorMessage(Messages.USER_CREATED_SUCCESSFULLY);
            loadUsers();
        },
        errorCallback = function () {
            showCreateErrorMessage(Messages.USER_CREATION_ERROR);
        },
        data = {name: user.name, dateOfBirthMillis: convertDateToMillis(user.dob)};
        post(BackendUtils.Services.USERS, data, successCallback, errorCallback);
    },
    onCreateButtonClick = function (){
        clearCreateErrorMessages();
        var user = readUser();
        if(user.isValid()){
            createUser(user);
        } else {
            showCreateErrorMessage(Messages.INVALID_USER_DATA);
        }
    },
    addKeyPressDatePickerPrevent = function (){
        $('[data-toggle="datepicker"]').keypress(function(event) {
            event.preventDefault();
            return false;
        });
    },
    registerEvents = function (){
        $("#createButton").click(onCreateButtonClick);
        addKeyPressDatePickerPrevent();
    },
    updateUser = function (scope){
        var userName = scope.parentElement.id,
            userDob = scope.parentElement.children.dob.value,
        data = {name: userName, dateOfBirthMillis: convertDateToMillis(userDob)},
        successCallback = function () {
            showCreateErrorMessage(Messages.USER_UPDATED_SUCCESSFULLY);
            loadUsers();
        },
        errorCallback = function () {
            showCreateErrorMessage(Messages.USER_UPDATED_ERROR);
        };
        put(BackendUtils.Services.USERS, data, successCallback, errorCallback);
    },
    deleteUser = function (scope){
        var userName = scope.parentElement.id,
        successCallback = function () {
            showCreateErrorMessage(Messages.USER_DELETED_SUCCESSFULLY);
            loadUsers();
        },
        errorCallback = function () {
            showCreateErrorMessage(Messages.USER_DELETED_ERROR);
        };

        deleteHttp(BackendUtils.Services.USERS+"/"+userName, successCallback, errorCallback);
    },
    formatDateToString= function(formattedDate) {
        var day = formattedDate.getDate();
        var month =  formattedDate.getMonth() + 1;
        var year = formattedDate.getFullYear();
        return (month>9 ? month : ('0'+month))+ "/" + (day>9 ? day : ('0'+day)) +"/" + year;
    },
    loadUsers = function (){
        var successCallback = function (users){
            if(users && users.length > 0){
                var usersHtml ='<ul>{listOfUsers}</ul>';
                var usersListHtml = "";
                users.forEach(function(each){
                    usersListHtml+='<li id="'+each.name+'">'+
                        '<input name="name" value="'+each.name+'" disabled/>'+
                        '<input name="dob" data-toggle="datepicker" value="' + formatDateToString(new Date(each.dateOfBirthMillis)) + '"/>'+
                        '<input value="Update" type="button" onclick="userController.updateUser(this);" />'+
                        '<input value="Delete" type="button" onclick="userController.deleteUser(this);" />'+
                        '</li>';
                });
                usersHtml = usersHtml.replace("{listOfUsers}", usersListHtml);
                $("#listOfUsers").html(usersHtml);
                initDatePicker();
                addKeyPressDatePickerPrevent();
            } else {
                $("#listOfUsers").html("");
            }
        },
        errorCallback = function () {
            showCreateErrorMessage(Messages.ERROR_LOADING_USERS);
        };

        get(BackendUtils.Services.USERS, successCallback, errorCallback);
    },
    init = function (){
        initDatePicker();
        registerEvents();
        loadUsers();
    };

	return {
	    init: init,
	    deleteUser: deleteUser,
        updateUser: updateUser
	}
};

$( document ).ready(function() {
    userController = new UserController();
    userController.init();
});