'use strict';

var competitionControllers = angular.module('competitionControllers', []);

competitionControllers.controller('CreateCompetitionController', ['$scope', '$http', '$location',
    function ($scope, $http, $location) {
        $scope.competitionName = null;

        $scope.createCompetition = function () {
            var data = {name: $scope.competitionName};

            $http.post('/rest/competition/create', JSON.stringify(data)).then(
                function successCallback(response) {
                    $location.path('/upload');
                },
                function errorCallback(response) {
                    console.log(response.statusText);
                }
            );
        };
    }
]);