'use strict';

var schoolAdminControllers = angular.module('schoolAdminControllers', []);

schoolAdminControllers.controller('CorrectSchoolNamesController', ['$scope', '$http', '$location', 'DancersWithUnknownSchools',
    function($scope, $http, $location, DancersWithUnknownSchools) {
        $scope.dancers = DancersWithUnknownSchools.query(
            function (data) {
                // Success
                if (!$scope.dancers || $scope.dancers.length < 1) {
                    $location.path('/groups');
                }
            },
            function (error) {
                console.log('Feil ved henting av dansere med ukjent skole - gikk noe galt ved filopplasting');
                console.log(error);
            }
        );

        $scope.updateSchoolNames = function() {
            var data = {updatedDancers: []};
            angular.forEach($scope.dancers, function(dancer, index) {
                var item = {
                    name: dancer.name,
                    schoolName: dancer.schoolName,
                    dancerId: dancer.dancerId
                };
                data.updatedDancers.push(item);
            });
            $http.post('/rest/schoolAdmin', JSON.stringify(data)).then(
                function successCallback(response) {
                    $location.path('/groups');
                },
                function errorCallback(response) {
                    console.log(response.statusText);
                }
            );
        };
    }
]);