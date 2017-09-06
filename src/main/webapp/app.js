'use strict';

var competitionApp = angular.module('competitionApp', [
    'ngRoute',
    'competitionControllers',

    /* upload */
    'uploadControllers',
    'uploadServices',
    'uploadDirectives',

    /* schoolAdmin */
    'schoolAdminControllers',
    'schoolAdminServices',

    /* groupAdmin */
    'groupAdminControllers',
    'groupAdminServices'
]);

competitionApp.config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {
        //$locationProvider.hashPrefix('!');

        $routeProvider.
            when('/create', {
                templateUrl: 'create/create.html',
                controller: 'CreateCompetitionController'
            }).
            when('/upload', {
                templateUrl: 'upload/upload.html',
                controller: 'FileUploadCtrl'
            }).
            when('/schoolAdmin', {
                templateUrl: 'schoolAdmin/correctSchoolNames.html',
                controller: 'CorrectSchoolNamesController'
            }).
            when('/groups', {
                templateUrl: 'groupAdmin/setupGroups.html',
                controller: 'SetupGroupsController'
            }).
            when('/groups2', {
                templateUrl: 'groupAdmin/setupGroups2.html',
                controller: 'SetupGroupsController2'
            }).
            otherwise({
                redirectTo: '/create'
            });
    }
]);