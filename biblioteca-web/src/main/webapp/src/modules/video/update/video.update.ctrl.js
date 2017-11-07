(
        function (ng) {
            var mod = ng.module("videosModule");
            mod.constant("videosContext", "api/999/videos");
            mod.constant("prestamoContext", "api/books");
            mod.controller('videosUpdateCtrl', ['$scope', '$http', 'videosContext', '$state', 'prestamoContext', '$rootScope', '$filter',
                function ($scope, $http, videosContext, $state, prestamoContext, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idVideo = $state.params.videoId;

                    //Consulto el autor a editar.
                       console.info(videosContext+'/' + idVideo);
                    $http.get(videosContext + '/' + idVideo).then(function (response) {
                        var video = response.data;
                        $scope.videoName = video.name;
                        $scope.videoDuracion = video.duracion;
                    });           
                    

                    $scope.createVideo = function () {                   
                        $http.put(videosContext + '/' + idVideo, {
                          name: $scope.videoName,
                   duracion: $scope.videoDuracion,
                        }).then(function (response) {                            
                            $state.go('videosList', {videoId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(angular);