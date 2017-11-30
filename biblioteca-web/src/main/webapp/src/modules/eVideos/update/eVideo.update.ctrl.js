(function (ng) {
    var mod = ng.module("eVideoModule");
    mod.constant("eVideosContext", "api/999/eVideos");
    mod.controller('eVideoUpdateCtrl', ['$scope', '$http', 'eVideosContext', '$state', '$rootScope', '$filter',
        function ($scope, $http, eVideosContext, $state,$rootScope) {
            $rootScope.edit = true;

            var idEVideo = $state.params.eVideoId;
            $scope.objeto = {};

            //Consulto el eVideo a editar.
            $http.get(eVideosContext + '/' + idEVideo).then(function (response) {
                var eVideo = response.data;
                $scope.objeto.name = eVideo.name;
                $scope.objeto.autor = eVideo.autor;
                $scope.objeto.duracion = eVideo.duracion;
                $scope.objeto.imagen = eVideo.imagen;
                $scope.objeto.direccion = eVideo.direccion;
            });

            $scope.createEVideo = function () {
                $http.put(eVideosContext + "/" + idEVideo, $scope.objeto).then(function (response) {
                    //eVideo created successfully
                    $state.go('eVideosList', {eVideoId: response.data.id}, {reload: true});
                });
            };


        }
    ]);
})(angular);