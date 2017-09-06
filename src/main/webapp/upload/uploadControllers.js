'use strict';

var uploadControllers = angular.module('uploadControllers', []);

uploadControllers.controller('FileUploadCtrl',
    ['$scope', '$rootScope', '$location', 'uploadManager',
        function ($scope, $rootScope, $location, uploadManager) {
            $scope.files = [];
            $scope.competition = "";
            $scope.percentage = 0;
            $scope.upload = function () {
                uploadManager.upload();
                $scope.files = [];
            };
            $rootScope.$on('fileAdded', function (e, call) {
                $scope.files.push(call);
                $scope.$apply();
            });
            $rootScope.$on('uploadProgress', function (e, call) {
                $scope.percentage = call;
                $scope.$apply();
            });
            $rootScope.$on('uploadDone', function (e, call) {
                $location.path('/schoolAdmin');
                $scope.$apply();
            });
        }
    ]
);