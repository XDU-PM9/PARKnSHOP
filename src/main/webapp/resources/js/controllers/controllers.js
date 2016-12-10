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
            $scope.apply = function () {

                var realImg = document.getElementById('realImg').files[0],
                    shopImg = document.getElementById('shopImg').files[0];

                var fd = new FormData();
                fd.append("realImg", realImg);
                fd.append('shopImg', shopImg);
                fd.append('ID', $scope.ID);
                fd.append('shopName', $scope.shopName);
                fd.append('shopDesc', $scope.shopDesc);
                fd.append('readName', $scope.readName);

                console.log(fd);

                OwnerService.apply(fd).then(function () {
                    alert('apply has sent successfully, please wait');
                }, function () {
                    alert('apply failed');
                });
            }

        }
    )
    .controller('CustomerCtrl', function ($scope, LoginService, $location, $rootScope) {
        var group = 'customer';
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
