angular.module('PNS.services', [])
    .factory('OwnerService', function ($http, $rootScope, $q, URLbase) {

        function application(ID, realName, realImg, shopName, shopImg, shopDesc) {
            var applicationURL = URLbase + '/owner/apply';
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: applicationURL,
                data: {
                    ID: ID,
                    realName: realName,
                    realImg: realImg,
                    shopName: shopName,
                    shopImg: shopImg,
                    shopDesc: shopDesc
                }
            }).success(function (data, status, headers) {

                console.log("user data=" + "");
                console.log(data);
                if (data.error == false) {
                    deferred.resolve(data);
                    $rootScope.user = data.data;
                    $rootScope.user.image = '../..' + data.data.image;
                    $rootScope.loged = true;
                } else {
                    alert(data.message)
                }
            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        }

        return {
            application: application
        };
    })
    .factory('CustomerService', function ($http, $rootScope, $q, URLbase) {
        var registURL = URLbase + 'customer/regist',
            loginURL = URLbase + 'customer/login',
            userUrl = 'http://localhost:4000/users';

        var regist = function (username, password, email) {
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: registURL,
                data: {
                    userName: username,
                    password: password,
                    email: email
                }
            }).success(function (data, status, headers) {

                console.log("user data=" + "");
                console.log(data);
                if (data.error == false) {
                    deferred.resolve(data);
                    $rootScope.user = data.data;
                    $rootScope.user.image = '../..' + data.data.image;
                    $rootScope.loged = true;
                } else {
                    alert(data.message)
                }
            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };
        var login = function (username, password) {
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: loginURL,
                data: {
                    userName: username,
                    password: password
                }
            }).success(function (data, status, headers) {

                console.log("user data=" + "");
                console.log(data);
                if (data.error == false) {
                    deferred.resolve(data);
                    $rootScope.user = data.data;
                    $rootScope.user.image = '../..' + data.data.image;
                    $rootScope.loged = true;
                } else {
                    alert(data.message)
                }
            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };
        var findAllUsers = function () {
            var deferred = $q.defer();

            $http({
                method: 'GET',
                url: userUrl
            }).success(function (data, status, headers) {

                console.log("users data=" + "");
                console.log(data);

                deferred.resolve(data);

            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };
        return {
            regist: regist,
            login: login,
            findAllUsers: findAllUsers
        };
    })
    .factory('AdminService', function ($http, $rootScope, $q, URLbase) {
        var registURL = URLbase + 'admin/regist',
            loginURL = URLbase + 'admin/login',
            userUrl = 'http://localhost:4000/users';

        var regist = function (username, password, email) {
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: registURL,
                data: {
                    userName: username,
                    password: password,
                    email: email
                }
            }).success(function (data, status, headers) {

                console.log("user data=" + "");
                console.log(data);
                if (data.error == false) {
                    deferred.resolve(data);
                    $rootScope.user = data.data;
                    $rootScope.user.image = '../..' + data.data.image;
                    $rootScope.loged = true;
                } else {
                    alert(data.message)
                }
            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };
        var login = function (username, password) {
            var deferred = $q.defer();
            console.log(username + password + '===========');

            $http({
                method: 'POST',
                url: loginURL,
                data: {
                    userName: username,
                    password: password
                }
            }).success(function (data, status, headers) {

                console.log("user data=" + "");
                console.log(data);
                if (data.error == false) {
                    deferred.resolve(data);
                    $rootScope.user = data.data;
                    $rootScope.user.image = '../..' + data.data.image;
                    $rootScope.loged = true;
                } else {
                    alert(data.message)
                }
            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };
        var findAllUsers = function () {
            var deferred = $q.defer();

            $http({
                method: 'GET',
                url: userUrl
            }).success(function (data, status, headers) {

                console.log("users data=" + "");
                console.log(data);

                deferred.resolve(data);

            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };
        return {
            regist: regist,
            login: login,
            findAllUsers: findAllUsers
        };
    })

    .factory('LoginService', function ($http, $rootScope, $q, URLbase) {

        var regist = function (group, username, password, email) {
            var registURL = URLbase + group + '/register'

            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: registURL,
                data: {
                    userName: username,
                    password: password,
                    email: email
                }
            }).success(function (data, status, headers) {

                console.log(data);
                if (data.error == false) {
                    deferred.resolve(data);
                    $rootScope.user = data.data;
                    $rootScope.user.image = '../..' + data.data.image;
                    $rootScope.loged = true;
                } else {
                    alert(data.message)
                }
            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };
        var login = function (group, username, password) {
            var loginURL = URLbase + group + '/login';
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: loginURL,
                data: {
                    userName: username,
                    password: password
                }
            }).success(function (data, status, headers) {

                console.log("user data=" + "");
                console.log(data);
                if (data.error == false) {
                    deferred.resolve(data);
                    $rootScope.user = data.data;
                    $rootScope.user.image = '../..' + data.data.image;
                    $rootScope.loged = true;
                } else {
                    alert(data.message)
                }
            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };
        var logout = function (group) {
            var loginURL = URLbase + group + '/logout';
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: loginURL
            }).success(function (data, status, headers) {
                deferred.resolve(data);
                $rootScope.user = null;
                $rootScope.user.image = null;
                $rootScope.loged = false;

            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        }
        return {
            regist: regist,
            login: login,
            logout: logout
        };
    })

    .factory('EmpService', ['$http', 'CustomerService', function ($http, CustomerService) {
        var rentInfo = [{
            toolID: 1,
            toolName: 1,
            num: 1,
            time: 1231232132
        }, {
            toolID: 1,
            toolName: 1,
            num: 1,
            time: 1231232132
        }, {
            toolID: 1,
            toolName: 1,
            num: 1,
            time: 1231232132
        }]
        return {
            getRentInfo: function () {
                return rentInfo;
            },
            returnTool: function (tool) {
                var userID = CustomerService.getUser().userID;
                var toolID = tool.toolID;
            }
        };
    }])
    .factory('ToolService', function ($http, $rootScope, $q) {
        var toolURL = 'http://localhost:4000/tool';

        var getAllTools = function () {
            var deferred = $q.defer();

            $http({
                method: 'GET',
                url: toolURL
            }).success(function (data, status, headers) {

                console.log("tools data=" + "");
                console.log(data);

                deferred.resolve(data);

            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };

        var findRentsByUserId = function (user_id) {
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: toolURL + '/rent/find',
                data: {
                    user_id: user_id
                }
            }).success(function (data, status, headers) {

                console.log("rents data=" + "");
                console.log(data);

                deferred.resolve(data);

            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };
        var getAllRentsAdmin = function () {
            var deferred = $q.defer();

            $http({
                method: 'GET',
                url: toolURL + '/rent'
            }).success(function (data, status, headers) {

                console.log("rents data=" + "");
                console.log(data);

                deferred.resolve(data);

            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });

            return deferred.promise;
        };
        var rent = function (user_id, tool_id, rent_num) {
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: toolURL + '/rent',
                data: {
                    user_id: user_id,
                    tool_id: tool_id,
                    rent_num: rent_num
                }
            }).success(function (data, status, headers) {

                console.log("rent data=" + "");
                console.log(data);
                if (data.message == "ok") {
                    deferred.resolve(data);

                } else {
                    alert(data + "")
                }
            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });
            return deferred.promise;
        }
        var returnTool = function (rent_id) {
            var deferred = $q.defer();

            $http({
                method: 'POST',
                url: toolURL + '/rent/return',
                data: {
                    rent_id: rent_id
                }
            }).success(function (data, status, headers) {

                console.log("return data=" + "");
                console.log(data);
                if (data.message == "ok") {
                    deferred.resolve(data);


                } else {
                    alert(data + "")
                }
            }).error(function (data, statue, headers) {
                deferred.reject(data)
            });
            return deferred.promise;
        }
        return {
            getAllTools: getAllTools,
            findRentsByUserId: findRentsByUserId,
            rent: rent,
            returnTool: returnTool,
            getAllRentsAdmin: getAllRentsAdmin
        };
    })
