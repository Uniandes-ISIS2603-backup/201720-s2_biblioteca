(
        function (ng) {
            var mod = ng.module("comentarioModule");
            mod.constant("comentarioContext", "api/999/comentarios");
            mod.controller('comentarioUpdateCtrl', ['$scope', '$http', 'comentariosContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, comentarioContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;

                    var idComentario = $state.params.comentarioId;

                    //Consulto el comentario a editar.
                    console.info(comentarioContext+'/' + idComentario);
                    $http.get(comentarioContext + '/' + idComentario).then(function (response) {
                        var comentario = response.data;
                        $scope.comentarioName = comentario.name;
                        $scope.comentarioCalificacion = comentario.calificacion;
                        $scope.fechaPublicado = comentario.fechaPublicado;
        
                    });        
                    

                    $scope.createComentario = function () {                   
                        $http.put(comentarioContext + "/" + idComentario, {
                            name: $scope.comentarioName,
                            calificacion: $scope.comentarioCalificacion,
                            fechaPublicado: $scope.fechaPublicado,
                        }).then(function (response) {                            
                            $state.go('comentariosList', {comentarioId: response.data.id}, {reload: true});
                        });
                    };                   
                }
            ]);
        }
)(windows.angular);