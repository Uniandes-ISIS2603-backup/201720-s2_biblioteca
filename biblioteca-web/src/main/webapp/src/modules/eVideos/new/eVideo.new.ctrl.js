(function (ng) {
    var mod = ng.module("eVideoModule");
    mod.constant("eVideosContext", "api/999/eVideos");
    mod.controller('eVideoNewCtrl', ['$scope', '$http', 'eVideosContext', '$state', '$rootScope',
        function ($scope, $http, eVideosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.objeto = {};
            $scope.createEVideo = function () {

                console.log($scope.objeto);
                $http.post(eVideosContext, $scope.objeto).then(function (response) {
                    //eVideo created successfully
                    $state.go('eVideosList', {eVideoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
})(angular);
