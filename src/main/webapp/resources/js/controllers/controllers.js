angular.module('PNS.controllers', ['PNS.services'])
    .controller('OwnerCtrl', function ($scope, LoginService, OwnerService, $location, $rootScope) {
        var group = 'owner';
        $scope.username = null;
        $scope.password = null;
        $scope.email = null;
        $scope.err_message = null;
        $scope.login = function () {
            LoginService.login(group, $scope.username, $scope.password).then(function (data) {
                $location.path('/' + group + '/home');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });
        }
        $scope.regist = function () {
            LoginService.regist(group, $scope.username, $scope.password, $scope.email).then(function (data) {
                alert('Please check your mailbox to activation');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });

        }
        $scope.logout = function () {
            console.log('is clicked');
            LoginService.logout(group).then(function (data) {
                $location.path('/' + group + '/index');
            }, function (data) {
            });
        }
        $scope.application = function () {
            OwnerService.application($scope.ID,
                $scope.realName,
                $scope.realImg,
                $scope.shopName,
                $scope.shopImg,
                $scope.shopDesc);
        }

    })
    .controller('CustomerCtrl', function ($scope, LoginService, $location, $rootScope) {
        var group = 'customer';
        $scope.username = null;
        $scope.password = null;
        $scope.email = null;
        $scope.err_message = null;
        $scope.login = function () {
            LoginService.login(group, $scope.username, $scope.password).then(function (data) {
                $location.path('/' + group + '/index');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });
        }
        $scope.regist = function () {
            LoginService.regist(group, $scope.username, $scope.password, $scope.email).then(function (data) {
                $location.path('/' + group + '/index');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });

        }
        $scope.logout = function () {
            console.log('is clicked');
            LoginService.logout(group).then(function (data) {
                $location.path('/' + group + '/index');
            }, function (data) {
            });
        }

    })
    .controller('AdminCtrl', function ($scope, LoginService, $location, $rootScope) {
        var group = 'admin';
        $scope.username = null;
        $scope.password = null;
        $scope.email = null;
        $scope.err_message = null;
        $scope.login = function () {
            LoginService.login(group, $scope.username, $scope.password).then(function (data) {
                $location.path('/' + group + '/home');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });
        }
        $scope.regist = function () {
            LoginService.regist(group, $scope.username, $scope.password, $scope.email).then(function (data) {
                $location.path('/' + group + '/index');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });

        }
        $scope.logout = function () {
            console.log('is clicked');
            LoginService.logout(group).then(function (data) {
                $location.path('/' + group + '/index');
            }, function (data) {
            });
        }

    })
