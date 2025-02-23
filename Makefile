.DEFAULT_GOAL := help
.PHONY: venv
.EXPORT_ALL_VARIABLES:

PROJECT_NAME = buscacode-admin-spring
ENV ?= dev ## dev, prod
ENV_FILE ?= .env


ifeq ($(ENV),prod)
	PORT     = 6001
	ENV_FILE = .env.production
else
	PORT     = 5001
endif

## INCLUDE TARGETS ##
include ${ENV_FILE}
include makefiles/flyway.mk
include makefiles/deploy.mk
include makefiles/help.mk
