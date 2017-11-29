(function (ng) {
    var mod = ng.module("videosModule");
    mod.constant("videosBibliotecaCrearContext", "api/999/videos/bibliotecaCrear");
    mod.controller('videoBibliotecaNewCtrl', ['$scope', '$http', 'videosBibliotecaCrearContext', '$state', '$rootScope',
        function ($scope, $http, videosBibliotecaCrearContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createVideo = function () {
                $http.post(videosBibliotecaCrearContext + "/"+ $state.params.bibliotecaId, {
                    name: $scope.videoName,
                   duracion: $scope.videoDuracion,
                   imagen:$scope.videoImagen,
                    autor:$scope.videoAutor
                }).then(function (response) {
                    //Video created successfully
                    $state.go('videosBibliotecaList', {videoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);
