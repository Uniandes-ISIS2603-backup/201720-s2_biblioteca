(function (ng) {
    var mod = ng.module("videosModule");
    mod.constant("videosContext", "api/999/videos");
    mod.controller('videoNewCtrl', ['$scope', '$http', 'videosContext', '$state', '$rootScope',
        function ($scope, $http, videosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createVideo = function () {
                $http.post(videosContext, {
                    name: $scope.videoName,
                   duracion: $scope.videoDuracion,
                }).then(function (response) {
                    //Usuario created successfully
                    $state.go('videosList', {videoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
