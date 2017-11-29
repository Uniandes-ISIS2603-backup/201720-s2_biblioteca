(function (ng) {
    var mod = ng.module("eVideoModule");
    mod.constant("eVideosContext", "api/999/eVideos");
    mod.controller('eVideoUpdateCtrl', ['$scope', '$http', 'eVideosContext', '$state', '$rootScope', '$filter',
        function ($scope, $http, eVideosContext, $state, VideosContext, $rootScope, $filter) {
            $rootScope.edit = true;

            var idEVideo = $state.params.eVideoId;

            //Consulto el eVideo a editar.
            $http.get(eVideosContext + '/' + idEVideo).then(function (response) {
                var eVideo = response.data;
                $scope.eVideoName = eVideo.name;
                $scope.eVideoAutor = eVideo.autor;
                $scope.eVideoDuracion = eVideo.duracion;
                $scope.eVideoImagen = eVideo.imagen;
            });

            $scope.createEVideo = function () {
                $http.put(eVideosContext + "/" + idEVideo, {
                    name: $scope.eVideoName,
                    autor: $scope.eVideoAutor,
                    duracion: $scope.eVideoDuracion,
                    imagen : $scope.eVideoImagen
                }).then(function (response) {
                    //eVideo created successfully
                    $state.go('eVideosList', {eVideoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
})(angular);