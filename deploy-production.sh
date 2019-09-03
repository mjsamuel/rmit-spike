#!/bin/bash

set -e

docker build -t gcr.io/${PROJECT_NAME_PRD}/${FRONTEND_DOCKER_IMAGE_NAME}:$TRAVIS_COMMIT $FRONTEND_BUILD_DIR
docker build -t gcr.io/${PROJECT_NAME_PRD}/${BACKEND_DOCKER_IMAGE_NAME}:$TRAVIS_COMMIT $BACKEND_BUILD_DIR

echo $GCLOUD_SERVICE_KEY_PRD | base64 --decode -i > ${HOME}/gcloud-service-key.json
gcloud auth activate-service-account --key-file ${HOME}/gcloud-service-key.json

gcloud --quiet config set project $PROJECT_NAME_PRD
gcloud --quiet config set container/cluster $CLUSTER_NAME_PRD
gcloud --quiet config set compute/zone ${CLOUDSDK_COMPUTE_ZONE}
gcloud --quiet container clusters get-credentials $CLUSTER_NAME_PRD

gcloud docker push gcr.io/${PROJECT_NAME_PRD}/${FRONTEND_DOCKER_IMAGE_NAME}
gcloud docker push gcr.io/${PROJECT_NAME_PRD}/${BACKEND_DOCKER_IMAGE_NAME}

yes | gcloud beta container images add-tag gcr.io/${PROJECT_NAME_PRD}/${FRONTEND_DOCKER_IMAGE_NAME}:$TRAVIS_COMMIT gcr.io/${PROJECT_NAME_PRD}/${FRONTEND_DOCKER_IMAGE_NAME}:latest
yes | gcloud beta container images add-tag gcr.io/${PROJECT_NAME_PRD}/${BACKEND_DOCKER_IMAGE_NAME}:$TRAVIS_COMMIT gcr.io/${PROJECT_NAME_PRD}/${BACKEND_DOCKER_IMAGE_NAME}:latest

kubectl config view
kubectl config current-context

kubectl set image deployment/${FRONTEND_KUBE_DEPLOYMENT_NAME} ${FRONTEND_KUBE_DEPLOYMENT_CONTAINER_NAME}=gcr.io/${PROJECT_NAME_PRD}/${FRONTEND_DOCKER_IMAGE_NAME}:$TRAVIS_COMMIT
kubectl set image deployment/${BACKEND_KUBE_DEPLOYMENT_NAME} ${BACKEND_KUBE_DEPLOYMENT_CONTAINER_NAME}=gcr.io/${PROJECT_NAME_PRD}/${BACKEND_DOCKER_IMAGE_NAME}:$TRAVIS_COMMIT
