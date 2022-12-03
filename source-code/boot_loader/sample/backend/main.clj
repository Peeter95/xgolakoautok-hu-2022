
(ns boot-loader.sample.backend.main
    (:require ; monotech-hq/monoset modules
              [elements.api]
              [forms.api]
              [layouts.popup-a.api]
              [layouts.surface-a.api]

              ; monotech-hq/project-developer modules
              [project-developer.api]

              ; monotech-hq/project-kit application modules
              [app.common.backend.api]
              [app.common.iso.api]
              [app.contents.backend.api]
              [app.home.backend.api]
              [app.settings.backend.api]
              [app.storage.backend.api]
              [app.user.backend.api]
              [app.views.backend.api]
              [app.website-config.backend.api]
              [app.website-contacts.backend.api]
              [app.website-menus.backend.api]
              [app.website-pages.backend.api]

              ; monotech-hq/project-kit website modules
              [site.website-config.backend.api]
              [site.website-contacts.backend.api]
              [site.website-impressum.backend.api]
              [site.website-menus.backend.api]

              ; sample project modules
              [app.sample.backend.api]
              [site.sample.backend.api]))
