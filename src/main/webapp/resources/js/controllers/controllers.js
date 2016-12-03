angular.module('PNS.controllers', ['PNS.services'])
    .controller('OwnerCtrl', function ($scope, LoginService, OwnerService, $location, $rootScope) {
        $scope.username = null;
        $scope.password = null;
        $scope.email = null;
        $scope.err_message = null;

        $('#login-button').click(function () {
            LoginService.login('owner', $scope.username, $scope.password).then(function (data) {
                $location.path('/owner/home');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });
        })
        $('#regist-button').click(function () {
            LoginService.regist('owner', $scope.username, $scope.password, $scope.email).then(function (data) {
                alert('Please check your mailbox to activation');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });

        })

        $scope.application = function () {
            OwnerService.application($scope.ID,
                $scope.realName,
                $scope.realImg,
                $scope.shopName,
                $scope.shopImg,
                $scope.shopDesc);
        }
        $scope.logout = function () {
            console.log('hello');
            LoginService.logout('owner').then(function (data) {
                $location.path('/customer/index');
            }, function (data) {
            });
        }
    })
    .controller('CustomerCtrl', function ($scope, LoginService, $location, $rootScope) {
        $scope.username = null;
        $scope.password = null;
        $scope.email = null;
        $scope.err_message = null;

        $('#login-button').click(function () {
            LoginService.login('customer', $scope.username, $scope.password).then(function (data) {
                $location.path('/customer/index');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });
        })
        $('#regist-button').click(function () {
            LoginService.regist('customer', $scope.username, $scope.password, $scope.email).then(function (data) {
                $location.path('/customer/index');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });

        })
        $scope.logout = function () {
            LoginService.logout('customer').then(function (data) {
                $location.path('/customer/index');
            }, function (data) {
            });
        }
    })
    .controller('AdminCtrl', function ($scope, LoginService, $location, $rootScope) {
        $scope.username = null;
        $scope.password = null;
        $scope.email = null;
        $scope.err_message = null;

        $('#login-button').click(function () {
            LoginService.login('admin', $scope.username, $scope.password).then(function (data) {
                $location.path('/admin/home');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });
        })
        $('#regist-button').click(function () {
            LoginService.regist('admin', $scope.username, $scope.password, $scope.email).then(function (data) {
                $location.path('/admin/home');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });

        })
        $scope.logout = function () {
            LoginService.logout('admin').then(function (data) {
                $location.path('/customer/index');
            }, function (data) {
            });
        }
    })

    .controller('HomeCtrl', function ($scope, LoginService, $location, $rootScope) {
        $scope.username = null;
        $scope.err_message = null;

        $('#login-button').click(function () {
            LoginService.signin($scope.username, $scope.password).then(function (data) {
                if (data.user.isAdmin == true) {
                    $location.path('/admin-tool')
                } else {
                    $location.path('/emp-rent')
                }
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });
        })
        $('#regist-button').click(function () {
            LoginService.signup($scope.username, $scope.password).then(function (data) {
                $location.path('/emp-rent');
            }, function (data) {
                console.log("user data=" + "");
                console.log(data)
                $scope.err_message = data.message;
            });

        })
    })
    .controller('EmpCtrl', function ($scope, $rootScope, $location, EmpService, ToolService) {
        $scope.rentInfo = null;
        $scope.toolInfo = null;

        $scope.rent = function (tool) {

            //console.log($rootScope.toolInfo.findByValue('_id',tool._id))
            if ($rootScope.user.isexpert) {
                ToolService.rent($rootScope.user._id, tool._id, tool.rent_num).then(function (data) {
                    $scope.rentInfo.push({
                        num: tool.rent_num,
                        tool_id: tool._id
                    })
                    $location.path('/progress')
                });
            } else if (tool.dept == $rootScope.user.dept) {
                ToolService.rent($rootScope.user._id, tool._id, tool.rent_num).then(function (data) {
                    $scope.rentInfo.push({
                        num: tool.rent_num,
                        tool_id: tool._id
                    });
                    $location.path('/progress')
                });
            } else {
                alert('您没有权限')
            }

        }
        ToolService.getAllTools().then(function (data) {
            $scope.toolInfo = data;
        }, function (data) {
            console.log("tools data=" + "");
            console.log(data);
        });
        ToolService.findRentsByUserId($rootScope.user._id).then(function (data) {
            $scope.rentInfo = data;
        }, function (data) {
            console.log("rents data=" + "");
            console.log(data);
        }, function (data) {
            console.log("err data=" + "");
            console.log(data)
        });
        $scope.returnTool = function (info) {
            ToolService.returnTool(info._id).then(function (data) {
                $scope.rentInfo.removeByValue('_id', info._id);
            }, function (data) {
                console.log("return data=" + "");
                console.log(data)
            })
        }
    })
    .controller('NavCtrl', function ($scope, $rootScope) {
        $scope.logout = function () {
            $rootScope.username = null;
            $rootScope.user_id = null;
            $rootScope.loged = false;
        }
    })
    .controller('ProgressCtrl', function ($scope, $rootScope, $interval) {
        var vm = $scope.vm = {};
        vm.value = 0;
        vm.style = 'progress-bar-danger';
        vm.showLabel = true;
        vm.striped = true;
        vm.currentSlogan = "点击开始";
        vm.slogans = ["1.请稍后,正在分配机器人 ", "2.机器人已分配,正在抓取工具 ", "3.货物已经放于传送带 ", "4.正在运输 ", "5.已完成"];
        $scope.selectValue = function () {
            console.log(vm.style);
        };
        var index = 0;
        var timeId = 500;
        $scope.count = function () {
            vm.currentSlogan = vm.slogans[index]
            var start = $interval(function () {
                vm.currentSlogan = vm.slogans[index]
                vm.value = ++index * 20;
                if (index > 4) {
                    $interval.cancel(start);
                }

            }, timeId);

        }

    })
