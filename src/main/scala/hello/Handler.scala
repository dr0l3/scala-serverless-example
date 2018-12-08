package hello

import io.circe.generic.auto._
import io.github.mkotsur.aws.handler.Lambda._
import io.github.mkotsur.aws.handler.Lambda
import com.amazonaws.services.lambda.runtime.Context
import io.github.mkotsur.aws.proxy
import io.github.mkotsur.aws.proxy.ProxyResponse
import ScalaHandler._

object ScalaHandler {
  case class Req(key1: Option[String], key2: Option[String], key3: Option[String])
  case class Resp(message: String, request: Req)
}

//class ScalaHandler extends Lambda[Req, Resp] {
//  override def handle(req: Req, context: Context): Either[Throwable, Resp] ={
//    println(context)
//    println(req)
//    val res = Right(Resp("And this is how you do it with mkotsur/aws-lambda-scala", req))
//    println(res)
//    res
//  }
//}

class ApiGatewayScalaHandler extends Proxy[Req, Resp] {
  override def handle(input: proxy.ProxyRequest[Req], c: Context): Either[Throwable, ProxyResponse[Resp]] = {
    println(c)
    println(input)
    val headers = Map("x-custom-response-header" -> "my custom response header value")
    val responseBodyOption = input.body.map(req => Resp("And this is how you do it with mkotsur/aws-lambda-scala", req))
    val res = Right(ProxyResponse(200, Some(headers), responseBodyOption))
    println(res)
    res
  }
}