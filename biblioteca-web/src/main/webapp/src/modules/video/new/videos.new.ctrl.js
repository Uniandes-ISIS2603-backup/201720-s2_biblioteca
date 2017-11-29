(function (ng) {
    var mod = ng.module("videosModule");
    mod.constant("videosContext", "api/999/videos");
    mod.controller('videoNewCtrl', ['$scope', '$http', 'videosContext', '$state', '$rootScope',
        function ($scope, $http, videosContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createVideo = function () {
                $http.post(videosContext, {
                    name: $scope.videoName,
                    autor: $scope.videoAutor,
                   duracion: $scope.videoDuracion,
                   imagen:$scope.videoImagen,
                }).then(function (response) {
                    //Video created successfully
                    $state.go('videosList', {videoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
