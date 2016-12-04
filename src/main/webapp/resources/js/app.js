var PNS = angular.module('PNS', [
    'ngRoute', 'PNS.services', 'PNS.controllers'
]);

PNS.config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when('/emp-rent', {
                    controller: 'EmpCtrl',
                    templateUrl: 'view/emp/rent.html'
                })
                .when('/emp-tool', {
                    controller: 'EmpCtrl',
                    templateUrl: 'view/emp/tool.html'
                })
                .when('/admin-tool', {
                    controller: 'AdminCtrl',
                    templateUrl: 'view/admin/tool.html'
                })
                .when('/admin-employee', {
                    controller: 'AdminCtrl',
                    templateUrl: 'view/admin/employee.html'
                })
                .when('/owner/login', {
                    controller: 'OwnerCtrl',
                    templateUrl: '../../resources/view/owner/login.html'
                })
                .when('/owner/regist', {
                    controller: 'OwnerCtrl',
                    templateUrl: '../../resources/view/owner/regist.html'
                })
                .when('/customer/index', {
                    controller: 'CustomerCtrl',
                    templateUrl: '../../resources/view/customer/index.html'
                })
                .when('/customer/login', {
                    controller: 'CustomerCtrl',
                    templateUrl: '../../resources/view/customer/login.html'
                })
                .when('/customer/regist', {
                    controller: 'CustomerCtrl',
                    templateUrl: '../../resources/view/customer/regist.html'
                })
                .when('/admin/home', {
                    controller: 'AdminCtrl',
                    templateUrl: '../../resources/view/admin/home.html'
                })
                .when('/admin/login', {
                    controller: 'AdminCtrl',
                    templateUrl: '../../resources/view/admin/login.html'
                })
                .when('/admin/regist', {
                    controller: 'AdminCtrl',
                    templateUrl: '../../resources/view/admin/regist.html'
                })
                .when('/owner/progress', {
                    controller: 'ProgressCtrl',
                    templateUrl: '../../resources/view/owner/progress.html'
                })
                .when('/owner/home', {
                    controller: 'OwnerCtrl',
                    templateUrl: '../../resources/view/owner/home.html'
                })
                .otherwise({redirectTo: '/customer/index'});
        }])
    .value('URLbase', "http://localhost:8080/");


//添加工具方法
Array.prototype.removeByValue = function (index, val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i][index] == val) {
            this.splice(i, 1);
            break;
        }
    }
}
Array.prototype.findByValue = function (index, val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i][index] == val) {
            return this[i];
        }
    }
}

var a = {
    "error": false,
    "message": "Login successfully",
    "date": "2016-12-01 15:24 09",
    "data": {"userName": "tester", "image": "/resources/images/portrait/a.png"}
};