package controllers

import play.api._
import play.api.mvc._

import play.api.libs.ws.WS

object Application extends Controller {

  def index() = Action{
    Ok(views.html.index())
  }

  def searchRepo(keyword: String) = Action {
    val feedUrl = "https://api.github.com/legacy/repos/search/" + keyword
    Async {
      WS.url(feedUrl).get().map { response =>
        Ok(response.json)
      }
    }
  }

  def contributors(owner: String, repo: String) = Action {
    val feedUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/contributors"
    Async {
      WS.url(feedUrl).get().map { response =>
        Ok(response.json)
      }
    }
  }

  def commits(owner: String, repo: String) = Action {
    val feedUrl = "https://api.github.com/repos/" + owner + "/" + repo + "/commits?per_page=100"
    Async {
      WS.url(feedUrl).get().map { response =>
        Ok(response.json)
      }
    }
  }
  
}