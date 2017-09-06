'use strict';

var groupAdminControllers = angular.module('groupAdminControllers', []);

groupAdminControllers.controller('SetupGroupsController', ['$scope', 'CompetitionGroups',
    function($scope, CompetitionGroups) {
        $scope.competitionGroups = CompetitionGroups.query();
    }
]),

groupAdminControllers.controller('SetupGroupsController2', ['$scope', 'CompetitionGroups2',
    function($scope, CompetitionGroups2) {
        $scope.competitionGroups2 = CompetitionGroups2.query();
    }
]);